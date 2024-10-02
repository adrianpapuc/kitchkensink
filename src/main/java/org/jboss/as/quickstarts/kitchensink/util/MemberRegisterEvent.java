package org.jboss.as.quickstarts.kitchensink.util;

import org.springframework.context.ApplicationEvent;

public class MemberRegisterEvent extends ApplicationEvent {
    public MemberRegisterEvent(Object source) {
        super(source);
    }
}