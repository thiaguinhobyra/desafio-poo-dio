package br.com.dio.desafio.service;

import br.com.dio.desafio.dominio.Bootcamp;

import java.util.List;

public interface BootcampService {
    void addBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps();
}
