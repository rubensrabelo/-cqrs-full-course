package io.github.rubensrabelo.project.mscommand.utils;

import org.modelmapper.ModelMapper;

public class ConverterUtil<S, T> {

    private final ModelMapper modelMapper;
    private final Class<S> sourceType;
    private final Class<T> targetType;

    public ConverterUtil(ModelMapper modelMapper,
                         Class<S> sourceType,
                         Class<T> targetType) {
        this.modelMapper = modelMapper;
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public T convertToTarget(S source) {
        return modelMapper.map(source, targetType);
    }

    public S convertToSource(T target) {
        return modelMapper.map(target, sourceType);
    }
}