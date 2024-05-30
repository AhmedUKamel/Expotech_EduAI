package org.ahmedukamel.eduai.saver.attachment;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.model.Attachment;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.AttachmentFormat;
import org.ahmedukamel.eduai.model.enumeration.AttachmentType;
import org.ahmedukamel.eduai.repository.AttachmentRepository;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class AttachmentSaver implements BiFunction<MultipartFile, AttachmentType, Attachment> {
    private final AttachmentRepository attachmentRepository;

    @Override
    public Attachment apply(MultipartFile file, AttachmentType type) {
        return this.apply(file, type, ContextHolderUtils.getUser());
    }

    public Attachment apply(MultipartFile file, AttachmentType type, User user) {
        String filename = FilenameUtils.getBaseName(file.getOriginalFilename());
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        AttachmentFormat format;
        try {
            format = AttachmentFormat.valueOf(Objects.requireNonNull(extension).toUpperCase());
        } catch (Exception e) {
            format = AttachmentFormat.OTHER;
        }

        Attachment attachment = Attachment
                .builder()
                .filename(filename)
                .extension(extension)
                .type(type)
                .format(format)
                .user(user)
                .build();

        return attachmentRepository.save(attachment);
    }
}