package org.wso2.gw.emulator.http.client.processors;

import org.wso2.gw.emulator.http.client.contexts.HttpClientProcessorContext;

public abstract class AbstractClientProcessor<T extends HttpClientProcessorContext> {

    public abstract void process(T processorContext);
}
