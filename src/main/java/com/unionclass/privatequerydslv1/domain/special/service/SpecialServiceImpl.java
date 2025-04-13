package com.unionclass.privatequerydslv1.domain.special.service;

import com.unionclass.privatequerydslv1.domain.special.dto.in.CreateSpecialReqDto;
import com.unionclass.privatequerydslv1.domain.special.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpecialServiceImpl implements  SpecialService {

    private final SpecialRepository specialRepository;

    @Transactional
    @Override
    public void createSpecial(CreateSpecialReqDto createSpecialReqDto) {
        specialRepository.save(createSpecialReqDto.toEntity());
    }

    @Override
    public String findUuidByName(String specialName) {
        return specialRepository.findByName(specialName).getUuid();
    }
}
