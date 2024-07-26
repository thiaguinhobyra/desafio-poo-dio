package br.com.dio.desafio.service;

import br.com.dio.desafio.dominio.Bootcamp;
import br.com.dio.desafio.dominio.Dev;

import java.util.List;

public interface DevService {
    void addDev(Dev dev);
    List<Dev> getAllDevs();
}
