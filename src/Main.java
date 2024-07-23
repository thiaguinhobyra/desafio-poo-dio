import br.com.dio.desafio.dominio.*;
import br.com.dio.desafio.service.BootcampService;
import br.com.dio.desafio.service.impl.BootcampServiceImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        Curso curso1 = new Curso();
        curso1.setTitulo("Curso java");
        curso1.setDescricao("Descrição curso java");
        curso1.setCargaHoraria(8);

        Curso curso2 = new Curso();
        curso2.setTitulo("curso js");
        curso2.setDescricao("descrição curso js");
        curso2.setCargaHoraria(4);

        Mentoria mentoria = new Mentoria();
        mentoria.setTitulo("mentoria de java");
        mentoria.setDescricao("descrição mentoria java");
        mentoria.setData(LocalDate.now());

        BootcampService bootcampService = new BootcampServiceImpl();

        Bootcamp bootcampJava = new Bootcamp();
        bootcampJava.setNome("Bootcamp Java Developer");
        bootcampJava.setDescricao("Descrição Bootcamp Java Developer");
        bootcampJava.getConteudos().add(curso1);
        bootcampJava.getConteudos().add(curso2);
        bootcampJava.getConteudos().add(mentoria);

        Bootcamp bootcampJs = new Bootcamp();
        bootcampJs.setNome("Bootcamp JavaScript");
        bootcampJs.setDescricao("Descrição Bootcamp JavaScript");
        bootcampJs.getConteudos().add(curso1);
        bootcampJs.getConteudos().add(curso2);
        bootcampJs.getConteudos().add(mentoria);

        bootcampService.addBootcamp(bootcampJava);
        bootcampService.addBootcamp(bootcampJs);

        List<Bootcamp> bootcamps = bootcampService.getAllBootcamps();
        Set<Dev> devsCadastrados = new HashSet<>();

        int perfil;

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
                Dev dev = null;
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
                    System.out.println("Dev cadastrado com sucesso: " + dev.getNome());
                } else {
                    System.out.println("Dev já possui cadastro. ");
                }

                System.out.println("Escolher bootcamp: ");
                for (int i = 0; i < bootcamps.size(); i++) {
                    Bootcamp bootcampLista = bootcamps.get(i);
                    System.out.println(i + 1 + ": " + bootcampLista.getNome() + " - " + bootcampLista.getDescricao());
                }

                int bootcampX = scanner.nextInt() - 1;
                scanner.nextLine();

                if (bootcampX >= 0 && bootcampX < bootcamps.size()) {
                    Bootcamp bootcampSelecao = bootcamps.get(bootcampX);

                    dev.inscreverBootcamp(bootcampSelecao);

                    System.out.println(dev.getNome() + " foi inscrito no bootcamp " + bootcampSelecao.getNome() + " com sucesso. ");
                } else {
                    System.out.println("Selecione um bootcamp válido. ");
                }

                System.out.println(dev.getNome() + " está inscrito nos seguintes bootcamps:");
                for (Conteudo conteudo : dev.getConteudosInscritos()) {
                    System.out.println(conteudo.getTitulo() + ": " + conteudo.getDescricao());
                }

            } else {
                System.out.println("Cadastros: ");
                System.out.println("1. Cursos: ");
                System.out.println("2. Mentorias: ");
                System.out.println("3. Bootcamps: ");

                int cadastro = scanner.nextInt();

                switch (cadastro) {
                    case 1:
                }
                System.out.println("Cadastrar Curso");
                System.out.println("Título:");
                curso1.setTitulo(scanner.nextLine());
                System.out.println("Descrição:");
                curso1.setDescricao(scanner.nextLine());
                System.out.println("Carga Horária:");
                curso1.setCargaHoraria(scanner.nextInt());
            }
        } while (perfil != 0);

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

    }

}
