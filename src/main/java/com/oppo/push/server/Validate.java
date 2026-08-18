package com.oppo.push.server;

import java.io.File;
import java.util.regex.Pattern;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Validate.class */
class Validate {
    private static final Pattern PATTERN_OBJECT_ID = Pattern.compile("[0-9a-fA-F]{24}");
    private static final Pattern PATTERN_REGISTRATION_ID = Pattern.compile("((([0-9A-Za-z]{2,10})_)?(.*)_)?[0-9a-fA-F]{32}");
    private static final Pattern PATTERN_ERROR_CODE = Pattern.compile("[0-9]+");
    private static final Pattern FILE_SUFFIX_IMAGE = Pattern.compile("[^*]+\\.(JPEG|JPG|PNG|)$");

    Validate() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T nonNull(T source, String field) {
        if (source == null) {
            throw new IllegalArgumentException(String.format("%s%s", field, " cannot be null"));
        }
        return source;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Integer validatePositiveInteger(Integer value, String field) {
        if (value == null || value.intValue() < 0) {
            throw new IllegalArgumentException(String.format("%s%s", field, " must be positive "));
        }
        return value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateLength(String source, String field, int limit) {
        if (source.length() > limit) {
            throw new IllegalArgumentException(String.format("%s%s%s", field, " length limit is ", Integer.valueOf(limit)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateSize(int value, String field, int limit) {
        if (value < 0 || value > limit) {
            throw new IllegalArgumentException(String.format("%s%s", field, " value is invalid"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEmpty(CharSequence source) {
        return source == null || source.length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean validateRegistrationId(String registrationId) {
        return !isEmpty(registrationId) && (PATTERN_REGISTRATION_ID.matcher(registrationId).matches() || PATTERN_OBJECT_ID.matcher(registrationId).matches());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean validateErrorCode(String errorCode) {
        return !isEmpty(errorCode) && PATTERN_ERROR_CODE.matcher(errorCode).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validatePictureType(File file) {
        if (file == null || !FILE_SUFFIX_IMAGE.matcher(file.getName().toUpperCase()).matches()) {
            throw new IllegalArgumentException("picture format must be PNG/JPEG/JPG");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateFileMaxSize(File file, long maxSize) {
        if (file == null || file.length() > maxSize) {
            throw new IllegalArgumentException(String.format("%s%s%s", " file Size limit is", Long.valueOf(maxSize / 1204), "KB"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validateStyle(Integer style) {
        if (style.intValue() < 1 || style.intValue() > 5) {
            throw new IllegalArgumentException(String.format("%s%s", " notification style can not choose ", style));
        }
    }
}
