# Board de Tarefas (Java)

**Este projeto é parte da aula _"Criando seu Board de Tarefas com Java"_ do bootcamp.**

Descrição
---------
Projeto simples de um board de tarefas (to-do board) em Java, para rodar no terminal. Implementa operações básicas:
- Criar tarefa
- Listar tarefas
- Atualizar tarefa (título, descrição, status)
- Remover tarefa
- Filtrar por status

Estrutura
---------
```
board-de-tarefas-java/
├─ src/com/board/
│  ├─ Task.java
│  ├─ TaskService.java
│  └─ Main.java
└─ README.md
```

Como compilar e executar
-----------------------
Requisitos: Java 11+ instalado.

1. Compile:
```bash
javac -d bin src/com/board/*.java
```

2. Execute:
```bash
java -cp bin com.board.Main
```

Observações
----------
- Armazenamento em memória (lista). Ao encerrar, as tarefas são perdidas.
- Projeto pensado para fins didáticos, para acompanhar a aula do bootcamp.
