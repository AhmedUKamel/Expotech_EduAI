package org.ahmedukamel.eduai.saver.notice;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.PathConstants;
import org.ahmedukamel.eduai.dto.notice.CreateNoticeRequest;
import org.ahmedukamel.eduai.model.Notice;
import org.ahmedukamel.eduai.model.NoticeDetail;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.NoticeRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.saver.file.FileSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class NoticeSaver implements BiFunction<CreateNoticeRequest, MultipartFile, Notice> {
    private final NoticeRepository noticeRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final FileSaver fileSaver;

    @Override
    public Notice apply(CreateNoticeRequest request, MultipartFile file) {
        final String pdf;
        try {
            pdf = fileSaver.save(file, PathConstants.NOTICE_PDFS_PATH);
        } catch (IOException exception) {
            throw new RuntimeException("Failed save pdf.", exception);
        }

        User user = DatabaseService.get(userRepository::findById, request.userId(), User.class);
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Notice notice = Notice
                .builder()
                .user(user)
                .school(school)
                .pdf(pdf)
                .build();

        NoticeDetail noticeDetail_en = NoticeDetail
                .builder()
                .title(request.title_en().strip())
                .description(request.description_en().strip())
                .language(Language.ENGLISH)
                .notice(notice)
                .build(),

                noticeDetail_ar = NoticeDetail
                        .builder()
                        .title(request.title_ar().strip())
                        .description(request.description_ar().strip())
                        .language(Language.ARABIC)
                        .notice(notice)
                        .build(),

                noticeDetail_fr = NoticeDetail
                        .builder()
                        .title(request.title_fr().strip())
                        .description(request.description_fr().strip())
                        .language(Language.FRENCH)
                        .notice(notice)
                        .build();

        Set<NoticeDetail> details = Set.of(noticeDetail_en, noticeDetail_ar, noticeDetail_fr);
        notice.setDetails(details);

        return noticeRepository.save(notice);
    }
}