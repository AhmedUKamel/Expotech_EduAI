package org.ahmedukamel.eduai.constant;

import java.nio.file.Path;

public interface PathConstants {
    Path NOTICE_PDFS_PATH = Path.of("/app/file/notice/pdfs");
//    Path EVENT_FILES_PATH = Path.of("D:\\app\\file\\event\\files");
    Path EVENT_FILES_PATH = Path.of("/app/file/event/files");
    Path POST_FILES_PATH = Path.of("/app/file/post/files");
    Path POST_COMMENT_FILES_PATH = Path.of("/app/file/post/comment/files");
    Path USER_ATTACHMENTS_PATH = Path.of("/app/file/user/attachments");
}