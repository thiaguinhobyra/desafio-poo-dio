package br.com.dio.desafio.service.impl;

import br.com.dio.desafio.dominio.Dev;
import br.com.dio.desafio.service.DevService;

import java.util.ArrayList;
import java.util.List;

public class DevServiceImpl implements DevService {
    private List<Dev> devs = new ArrayList<>();

    @Override
    public void addDev(Dev dev) {
        if (!devs.contains(dev)) {
            devs.add(dev);
        }
    }

    @Override
    public List<Dev> getAllDevs() {
        return devs;
    }
}
