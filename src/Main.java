import br.com.dio.desafio.dominio.*;
import br.com.dio.desafio.service.BootcampService;
import br.com.dio.desafio.service.impl.BootcampServiceImpl;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Curso curso = new Curso();
        Mentoria mentoria = new Mentoria();
        boolean logado = false;
        BootcampService bootcampService = new BootcampServiceImpl();

        
        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        List<Conteudo> cursos = curso.getAllConteudos();
        List<Conteudo> mentorias = mentoria.getAllConteudos();
        Set<Dev> devsCadastrados = new HashSet<>();

        Curso curso1 = new Curso();
        curso1.setTitulo("Curso java");
        curso1.setDescricao("Descrição curso java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("curso js");
        curso2.setDescricao("descrição curso js");
        curso2.setCargaHoraria(4);

        Mentoria mentoria1 = new Mentoria();
        mentoria1.setTitulo("mentoria de java");
        mentoria1.setDescricao("descrição mentoria java");
        mentoria1.setData(LocalDate.now());


        Bootcamp bootcampJava = new Bootcamp();
        bootcampJava.setNome("Bootcamp Java Developer");
        bootcampJava.setDescricao("Descrição Bootcamp Java Developer");
        bootcampJava.getConteudos().add(curso1);
        bootcampJava.getConteudos().add(mentoria1);

        Bootcamp bootcampJs = new Bootcamp();
        bootcampJs.setNome("Bootcamp JavaScript");
        bootcampJs.setDescricao("Descrição Bootcamp JavaScript");
        bootcampJs.getConteudos().add(curso2);
        bootcampJs.getConteudos().add(mentoria1);

        bootcampService.addBootcamp(bootcampJava);
        bootcampService.addBootcamp(bootcampJs);

        Dev devCamila = new Dev();
        devCamila.setNome("Camila");
        devCamila.inscreverBootcamp(bootcampJava);
        System.out.println("Conteúdos Inscritos Camila:" + devCamila.getConteudosInscritos());
        devCamila.progredir();
        devCamila.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos Camila:" + devCamila.getConteudosInscritos());
        System.out.println("Conteúdos Concluídos Camila:" + devCamila.getConteudosConcluidos());
        System.out.println("XP:" + devCamila.calcularTotalXp());

        System.out.println("-------");

        Dev devJoao = new Dev();
        devJoao.setNome("Joao");
        devJoao.inscreverBootcamp(bootcampJs);
        System.out.println("Conteúdos Inscritos João:" + devJoao.getConteudosInscritos());
        devJoao.progredir();
        devJoao.progredir();
        devJoao.progredir();
        System.out.println("-");
        System.out.println("Conteúdos Inscritos João:" + devJoao.getConteudosInscritos());
        System.out.println("Conteúdos Concluidos João:" + devJoao.getConteudosConcluidos());
        System.out.println("XP:" + devJoao.calcularTotalXp());



        int perfil;
        Dev dev = null;

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Sou Dev: ");
            System.out.println("2. Sou Administrativo: ");
            String entrada = scanner.nextLine();

            try {
                perfil = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                perfil = 0;
            }

            if(perfil == 1) {
                if (!logado) {

                    System.out.println("Cadastrar Dev: ");
                    String newDev = scanner.nextLine();
                    for (Dev existingDev : devsCadastrados) {
                        if (existingDev.getNome().equals(newDev)) {
                            dev = existingDev;
                            break;
                        }
                    }

                    if (dev == null) {
                        dev = new Dev();
                        dev.setNome(newDev);
                        devsCadastrados.add(dev);
                        logado = true;
                        System.out.println("Dev cadastrado com sucesso: " + dev.getNome());
                    } else {
                        System.out.println("Dev já possui cadastro. ");
                    }
                } else {
                    System.out.println("Escolher bootcamp: ");
                    for (int i = 0; i < bootcamps.size(); i++) {
                        Bootcamp bootcampLista = bootcamps.get(i);
                        System.out.println(i + 1 + ": " + bootcampLista.getNome() + " - " + bootcampLista.getDescricao());
                    }

                    int bootcampX = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (bootcampX >= 0 && bootcampX < bootcamps.size()) {
                        Bootcamp bootcampSelecao = bootcamps.get(bootcampX);

                        dev.getBootcampsInscritos().add(bootcampSelecao);
                        dev.inscreverBootcamp(bootcampSelecao);

                        System.out.println(dev.getNome() + " foi inscrito no bootcamp " + bootcampSelecao.getNome() + " com sucesso. ");
                    } else {
                        System.out.println("Selecione um bootcamp válido. ");
                    }

                    System.out.println(dev.getNome() + " está inscrito nos seguintes bootcamps: ");
                    System.out.println("Conteúdos Inscritos " + dev.getNome() +": " + dev.getConteudosInscritos());
                    dev.progredir();
                    dev.progredir();
                    System.out.println("-");
                    System.out.println("Conteúdos Inscritos " + dev.getNome() +": " + dev.getConteudosInscritos());
                    System.out.println("Conteúdos Concluídos " + dev.getNome() +": " + dev.getConteudosConcluidos());
                    System.out.println("XP:" + dev.calcularTotalXp());

                    System.out.println("-------");
                    for (Bootcamp bootcamp : dev.getBootcampsInscritos()) {
                        System.out.println(bootcamp.getNome() + ": " + bootcamp.getDescricao());

                        System.out.println(" Conteúdos: ");
                        System.out.println(bootcamp.getConteudos());

                    }
                }


            } else {
                System.out.println("Cadastros: ");
                System.out.println("1. Cursos ");
                System.out.println("2. Mentorias ");
                System.out.println("3. Bootcamps ");
                System.out.println("4. Sair ");

                int cadastro = scanner.nextInt();

                switch (cadastro) {
                    case 1:
                        scanner.nextLine();
                        Curso newCurso = new Curso();

                        System.out.println("Título: ");
                        newCurso.setTitulo(scanner.nextLine());

                        System.out.println("Descrição: ");
                        newCurso.setDescricao(scanner.nextLine());

                        System.out.println("Carga Horária: ");
                        newCurso.setCargaHoraria(scanner.nextInt());

                        curso.getAllConteudos().add(newCurso);
                        System.out.println(newCurso.toString());

                        break;
                    case 2:
                        scanner.nextLine();
                        Mentoria newMentoria = new Mentoria();

                        System.out.println("Título: ");
                        newMentoria.setTitulo(scanner.nextLine());

                        System.out.println("Descrição: ");
                        newMentoria.setDescricao(scanner.nextLine());

                        System.out.println("Data: ");
                        newMentoria.setData(LocalDate.now());

                        mentoria.getAllConteudos().add(newMentoria);
                        System.out.println(newMentoria.toString());
                        break;

                    case 3:
                        scanner.nextLine();
                        Bootcamp newBootcamp = new Bootcamp();
                        System.out.println("Nome: ");
                        newBootcamp.setNome(scanner.nextLine());
                        System.out.println("Descrição: ");
                        newBootcamp.setDescricao(scanner.nextLine());

                        if (cursos.size() > 0) {
                            System.out.println("Adicionar cursos ao Bootcamp: ");

                            for (int i = 0; i < cursos.size(); i++) {
                                Conteudo conteudoLista = cursos.get(i);
                                System.out.println(i + 1 + ": " + conteudoLista.getTitulo() + " - " + conteudoLista.getDescricao());
                            }
                            int cursoX = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (cursoX >= 0 && cursoX < cursos.size()) {
                                Conteudo cursoSelecao = cursos.get(cursoX);
                                newBootcamp.getConteudos().add(cursoSelecao);
                                bootcampService.addBootcamp(newBootcamp);

                                System.out.println(newBootcamp.getConteudos() + " adicionado(s) ao bootcamp com sucesso. ");
                            } else {
                                System.out.println("Selecione um bootcamp válido. ");
                            }
                        }

                        if (mentorias.size() > 0) {
                            System.out.println("Adicionar mentorias ao Bootcamp: ");

                            for (int i = 0; i < mentorias.size(); i++) {
                                Conteudo conteudoLista = mentorias.get(i);
                                System.out.println(i + 1 + ": " + conteudoLista.getTitulo() + " - " + conteudoLista.getDescricao());
                            }
                            int mentoriaX = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (mentoriaX >= 0 && mentoriaX < mentorias.size()) {
                                Conteudo mentoriaSelecao = mentorias.get(mentoriaX);
                                newBootcamp.getConteudos().add(mentoriaSelecao);
                                bootcampService.addBootcamp(newBootcamp);

                                System.out.println(newBootcamp.getConteudos() + " adicionado(s) ao bootcamp com sucesso. ");
                            } else {
                                System.out.println("Selecione um bootcamp válido. ");
                            }
                        }

                        break;
                    case 4:
                        break;
                }
            }

        } while (perfil != 3);
    }
}
