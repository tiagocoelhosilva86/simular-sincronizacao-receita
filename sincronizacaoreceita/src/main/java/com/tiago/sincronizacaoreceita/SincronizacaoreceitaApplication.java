package com.tiago.sincronizacaoreceita;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SincronizacaoreceitaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SincronizacaoreceitaApplication.class, args);
		ReceitaService receitaService = new ReceitaService();


		if(args.length != 0){


			Reader reader = Files.newBufferedReader(Paths.get(args[0]));

			CsvToBean<CsvConta> csvToBean = new CsvToBeanBuilder(reader)
					.withSeparator(';')
					.withType(CsvConta.class)
					.build();

			List<CsvConta> contas = csvToBean.parse();
			List<String[]> retorno = new ArrayList<>();

			String[] csvCabecario = new String[5];
			csvCabecario[0] = "agencia";
			csvCabecario[1] = "conta";
			csvCabecario[2] = "saldo";
			csvCabecario[3] = "status";
			csvCabecario[4] = "service retorno";
			retorno.add(csvCabecario);

			for (CsvConta conta : contas){
				try {
					boolean atualizou = receitaService.atualizarConta(conta.getAgencia(), conta.getConta(),
							Double.valueOf(conta.getSaldo().replace(',', '.')), conta.getStatus());

					retorno.add(conta.toCsvString(String.valueOf(atualizou)));
				}catch (RuntimeException ex){
					retorno.add(conta.toCsvString(ex.getMessage()));
				}catch (Exception ex){
					retorno.add(conta.toCsvString(ex.getMessage()));
				}

			}
			writeAllLines(retorno);
		}else {

			System.out.println("erro sem arquivos como parametro");

		}






	}

	public static void writeAllLines(List<String[]> lines) throws Exception {
		try (CSVWriter writer = new CSVWriter(new FileWriter("saida.csv"))) {
			writer.writeAll(lines);
		}
	}

}
