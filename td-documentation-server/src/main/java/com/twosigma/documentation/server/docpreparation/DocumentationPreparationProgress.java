package com.twosigma.documentation.server.docpreparation;

import java.util.Map;

public interface DocumentationPreparationProgress {
    void reportProgress(String message, Map<String, String> keyValues, int progressPercent);
}