package com.board;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskService service = new TaskService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1" -> createTask();
                case "2" -> listTasks();
                case "3" -> updateTask();
                case "4" -> deleteTask();
                case "5" -> filterByStatus();
                case "0" -> {
                    System.out.println("Saindo... Até logo!");
                    running = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("=== BOARD DE TAREFAS ===");
        System.out.println("1) Criar tarefa");
        System.out.println("2) Listar tarefas");
        System.out.println("3) Atualizar tarefa");
        System.out.println("4) Remover tarefa");
        System.out.println("5) Filtrar por status");
        System.out.println("0) Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void createTask() {
        System.out.print("Título: ");
        String title = scanner.nextLine().trim();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine().trim();
        Task t = service.create(title, desc);
        System.out.println("Tarefa criada: " + t);
    }

    private static void listTasks() {
        List<Task> all = service.listAll();
        if (all.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("--- Tarefas ---");
        all.forEach(t -> System.out.println(t.toString()));
    }

    private static void updateTask() {
        System.out.print("ID da tarefa: ");
        int id = readInt();
        Optional<Task> opt = service.findById(id);
        if (opt.isEmpty()) {
            System.out.println("Tarefa não encontrada.");
            return;
        }
        Task t = opt.get();
        System.out.println("Tarefa atual: " + t);
        System.out.print("Novo título (enter para manter): ");
        String title = scanner.nextLine();
        System.out.print("Nova descrição (enter para manter): ");
        String desc = scanner.nextLine();
        System.out.print("Novo status (TODO, IN_PROGRESS, DONE) ou enter para manter: ");
        String st = scanner.nextLine().trim();
        Task.Status status = null;
        if (!st.isBlank()) {
            try {
                status = Task.Status.valueOf(st);
            } catch (IllegalArgumentException e) {
                System.out.println("Status inválido. Mantendo status atual.");
            }
        }
        boolean ok = service.update(id, title.isBlank() ? null : title, desc.isBlank() ? null : desc, status);
        System.out.println(ok ? "Atualizada com sucesso." : "Falha ao atualizar.");
    }

    private static void deleteTask() {
        System.out.print("ID da tarefa: ");
        int id = readInt();
        boolean removed = service.delete(id);
        System.out.println(removed ? "Tarefa removida." : "Tarefa não encontrada.");
    }

    private static void filterByStatus() {
        System.out.print("Status (TODO, IN_PROGRESS, DONE): ");
        String st = scanner.nextLine().trim();
        try {
            Task.Status status = Task.Status.valueOf(st);
            List<Task> list = service.listByStatus(status);
            if (list.isEmpty()) {
                System.out.println("Nenhuma tarefa com status " + status);
                return;
            }
            list.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido.");
        }
    }

    private static int readInt() {
        while (true) {
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Digite novamente: ");
            }
        }
    }
}
