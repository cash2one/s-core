package com.seo.core.facade.upload;

import com.seo.core.model.Doorway;
import com.seo.message.MessageNotifier;

public interface UploadFacade extends MessageNotifier {
    void uploadDoorway(Doorway doorway);
    void uploadRedirectScript(Doorway doorway);
}
