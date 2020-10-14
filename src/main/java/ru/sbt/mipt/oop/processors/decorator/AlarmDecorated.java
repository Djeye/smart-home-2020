package ru.sbt.mipt.oop.processors.decorator;

import ru.sbt.mipt.oop.processors.Process;
import ru.sbt.mipt.oop.processors.SignalingEventProcessor;

public class AlarmDecorated implements ProcessDecorator {

    @Override
    public Process decorate(Process processor) {
        if (processor instanceof SignalingEventProcessor) {
            return processor;
        }
        return new AlarmDecoratedProcessor(processor);
    }
}
