# Copilot Instructions for roteiro08 (LEDA Roteiro 08)

## Visão Geral
Este projeto é um roteiro de estruturas de dados (LEDA/UFCG) focado em listas encadeadas e pilhas, implementado em Java. O código está organizado em pacotes `adt.linkedList` e `adt.stack`, com implementações e testes separados.

## Estrutura do Projeto
- **src/main/java/adt/linkedList/**: Implementações de listas encadeadas (simples, duplamente, recursivas).
- **src/main/java/adt/stack/**: Implementações de pilhas baseadas em listas.
- **src/test/java/adt/linkedList/** e **src/test/java/adt/stack/**: Testes JUnit para as implementações.
- **pom.xml**: Gerenciamento de dependências e build via Maven (Java 8, JUnit 4.13.1).

## Convenções e Padrões
- **Nó Sentinela (NIL)**: Listas usam nós sentinela para indicar fim da lista. Métodos como `isNIL()` são comuns.
- **Null Safety**: Métodos como `insert` ignoram elementos nulos. Métodos de busca retornam `null` se não encontrar.
- **Testes**: Testes unitários seguem padrão JUnit 4. Instanciação das listas deve ser ajustada pelo aluno no método `getImplementations()` dos testes.
- **Exceções**: Métodos não implementados lançam `UnsupportedOperationException`.

## Fluxo de Desenvolvimento
- **Build/Testes**: Use Maven para compilar e rodar testes:
  - Compilar: `mvn compile`
  - Testar: `mvn test`
- **Adição de Implementações**: Implemente as interfaces em `adt.linkedList` e `adt.stack`. Atualize os testes para usar suas implementações.
- **Padrão de Busca**: Métodos de busca percorrem a lista até encontrar o elemento ou o nó NIL. Exemplo:
  ```java
  SingleLinkedListNode<T> aux = this.head;
  while (!aux.isNIL() && !aux.data.equals(element)) {
      aux = aux.next;
  }
  return aux.isNIL() ? null : aux.data;
  ```

## Integrações e Dependências
- **JUnit**: Para testes unitários.
- **Maven**: Para build e gerenciamento de dependências.
- **Repositório LEDA**: Plugin Maven customizado para submissão (ver `<pluginRepositories>` no `pom.xml`).

## Dicas para Agentes AI
- Sempre siga as assinaturas das interfaces.
- Respeite o uso de NIL/sentinela para evitar NullPointerException.
- Não altere a estrutura dos testes, apenas ajuste a implementação instanciada.
- Métodos que retornam arrays devem criar arrays do tipo correto e tamanho igual ao da lista.
- Use diamond operator (`<>`) para instanciar genéricos.

## Exemplos de Arquivos-Chave
- `src/main/java/adt/linkedList/SingleLinkedListImpl.java`
- `src/main/java/adt/linkedList/DoubleLinkedListImpl.java`
- `src/test/java/adt/linkedList/StudentLinkedListTest.java`
- `pom.xml`

---
Seções ou padrões não claros? Peça exemplos ou esclarecimentos ao usuário para refinar estas instruções.
