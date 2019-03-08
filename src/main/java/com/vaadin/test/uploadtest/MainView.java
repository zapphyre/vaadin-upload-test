package com.vaadin.test.uploadtest;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.IOException;

/**
 * Created by mmanco89@gmail.com on 8.3.2019.
 */
@Route
public class MainView extends VerticalLayout {
    private Upload upload;
    private MemoryBuffer buffer;

    public MainView() {
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);

        upload.setMaxFiles(1);
        upload.setAutoUpload(true);
        upload.setId("qwr234");
        upload.setDropAllowed(false);

        upload.addStartedListener(evt -> System.out.println("started"));
        upload.addFailedListener(evt -> System.out.println("failed"));
        upload.addFinishedListener(evt -> System.out.println("finished"));
        upload.addProgressListener(evt -> System.out.println("progress"));

        upload.addSucceededListener(evt -> {
            byte[] buf = new byte[(int) evt.getContentLength()];

            try {
                buffer.getInputStream().read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("success");
        });

        add(upload);
    }
}