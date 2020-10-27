package ru.sbt.mipt.oop.processors.decorator;

import ru.sbt.mipt.oop.processors.Process;

public interface ProcessDecorator {
    Process decorate(Process processor);
}
