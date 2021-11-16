package br.inatel.cdg.funcionario;

public class Funcionario {

    private int id;
    private int numFilhos;
    private double salario;

    public Funcionario(int id, double salario, int numFilhos) {
        this.id = id;
        this.salario = salario;
        this.numFilhos = numFilhos;
    }

    //Getters e setters
    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public int getNumFilhos() {
        return numFilhos;
    }

}