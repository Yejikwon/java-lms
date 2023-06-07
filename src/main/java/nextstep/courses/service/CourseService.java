package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFeeType;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.SessionRepository;
import nextstep.courses.repository.StudentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public class CourseService {

    private final CourseRepository courseRepository;

    private final SessionRepository sessionRepository;

    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, SessionRepository sessionRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.studentRepository = studentRepository;
    }

    public Course findCourseById(Long courseId) {
        return courseRepository
                .findById(courseId)
                .initSessions(sessionRepository.findByCourseId(courseId));
    }

    @Transactional
    public void createCourse(NsUser nsUser, String title) {
        courseRepository.save(new Course(title, nsUser.getId()));
    }

    @Transactional
    public void createSession(
            NsUser nsUser,
            Long courseId,
            String sessionTitle,
            Long sessionNumber,
            LocalDate startDate,
            LocalDate endDate,
            String url,
            SessionFeeType sessionFeeType,
            SessionStatus sessionStatus,
            int capacity
    ) {
        Course course = findCourseById(courseId);
        Session session = course.createSession(courseId, nsUser, sessionTitle, sessionNumber, startDate, endDate, url, sessionFeeType, sessionStatus, capacity);
        sessionRepository.create(session);
    }

    @Transactional
    public void registerSession(Long sessionId, NsUser nsUser) {
        Session registered = sessionRepository.findById(sessionId)
                .initUsers(studentRepository.findBySessionId(sessionId))
                .validateRegister(nsUser);
        studentRepository.registerSession(registered.getId(), nsUser.getId());
    }
}