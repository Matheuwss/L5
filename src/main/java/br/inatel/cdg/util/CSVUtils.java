package br.inatel.cdg.util;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import br.inatel.cdg.funcionario.Funcionario;

public class CSVUtils {

    //FUNÇÃO P/ MAPEAR CADA ENTRADA DO CSV PARA UMA LISTA (ARRAY) DE FUNCIONÁRIOS.
    private static ArrayList<Funcionario> readFileAndCreateFuncs(Path arquivo){
        try {
            List<String> conteudo = Files.readAllLines(arquivo);
            int size = conteudo.size();

            ArrayList<Funcionario> funcs = new ArrayList<>(size);
            conteudo.forEach((linha) -> {
                String[] linhaQuebrada = linha.split(",");
                if (!linhaQuebrada[0].matches("[A-Za-z ]*")) {
                    Funcionario aux = new Funcionario(Integer.parseInt(linhaQuebrada[0]), Double.parseDouble(linhaQuebrada[4]), Integer.parseInt(linhaQuebrada[3]));
                    funcs.add(aux);
                }
            });
            return funcs;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //FUNÇÃO P/ FILTRAR O ARQUIVO - eliminar os funcionários que não possuem filhos.
    private static ArrayList<Funcionario> FilterFuncs(ArrayList<Funcionario> funcs){
        funcs.removeIf(func -> func.getNumFilhos() <= 0);
        return funcs;
    }

    //MÉTODO FINAL - P/ gerar um novo arquivo CSV filtrado (func_filtrado).
    public static void gerarCSVFuncSemFilhos(Path arquivo){
        ArrayList<Funcionario> funcs = null;
        try{
            funcs = readFileAndCreateFuncs(arquivo);
        }catch(Exception e){
            System.out.println(e);
        }

        try {
            funcs = FilterFuncs(funcs);
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Erro ao filtrar funcionarios");
        }

        String nomeArquivo = "func_filtrado.csv";
        String frase = "ID,FILHOS,SALARIO\n";
        Path arquivoFinal = Paths.get(nomeArquivo);
        try {
            Files.writeString(arquivoFinal, frase);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try{
            for(Funcionario funcionario : funcs){
                frase = funcionario.getId() + "," + funcionario.getNumFilhos() + "," + funcionario.getSalario() + "\n";
                try {
                    Files.writeString(arquivoFinal, frase, StandardOpenOption.APPEND);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

}