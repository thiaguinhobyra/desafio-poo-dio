package br.com.dio.desafio.service.impl;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.service.BootcampService;

import java.util.ArrayList;
import java.util.List;

public class BootcampServiceImpl implements BootcampService {
    private List<Bootcamp> bootcamps = new ArrayList<>();

    @Override
    public void addBootcamp(Bootcamp bootcamp) {
        if (!bootcamps.contains(bootcamp)) {
            bootcamps.add(bootcamp);
        }
    }

    @Override
    public List<Bootcamp> getAllBootcamps() {
        return bootcamps;
    }
}
