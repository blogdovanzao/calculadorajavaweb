package com.example.energia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnergiaController {

    @GetMapping("/")
    public String formulario() {
        return "formulario";
    }

    @PostMapping("/resultado")
    public String resultado(
        @RequestParam("nome") String nome,
        @RequestParam("consumo") float[] consumo,
        Model model) {
        
        // Calcula o consumo total
        float consumoTotal = 0;
        int mesMaiorConsumo = 0;
        int mesMenorConsumo = 0;
        
        for (int i = 0; i < consumo.length; i++) {
            consumoTotal += consumo[i];
            if (consumo[i] > consumo[mesMaiorConsumo]) {
                mesMaiorConsumo = i;
            }
            if (consumo[i] < consumo[mesMenorConsumo]) {
                mesMenorConsumo = i;
            }
        }

        // Mapeia o número do mês para nome do mês
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", 
                          "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        
        // Adiciona atributos ao modelo para serem exibidos no Thymeleaf
        model.addAttribute("nome", nome);
        model.addAttribute("consumoTotal", consumoTotal);
        model.addAttribute("mesMaiorConsumo", meses[mesMaiorConsumo]);
        model.addAttribute("mesMenorConsumo", meses[mesMenorConsumo]);

        return "resultado";
    }
}
