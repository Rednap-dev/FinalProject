package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.generator.CodeGenerator;
import com.rednap.finalproject.model.entity.CodeEntity;
import com.rednap.finalproject.model.entity.OrderEntity;
import com.rednap.finalproject.repository.CodeRepository;
import com.rednap.finalproject.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;
    private final CodeGenerator codeGenerator;

    @Override
    public boolean validate(final String code, final Long orderId) {
        final Optional<CodeEntity> codeEntity = codeRepository.findByOrderEntityId(orderId);

        if(codeEntity.isEmpty()) {
            return false;
        }

        if(codeEntity.get().getCode().equals(code)) {
            codeRepository.delete(codeEntity.get());
            return true;
        }

        return false;
    }

    @Override
    public String generateCode(final OrderEntity orderEntity) {
        final String code = codeGenerator.generate();
        final CodeEntity codeEntity = new CodeEntity();
        codeEntity.setCode(code);
        codeEntity.setOrderEntity(orderEntity);
        codeRepository.save(codeEntity);
        return code;
    }
}
