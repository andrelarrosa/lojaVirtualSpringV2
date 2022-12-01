package com.dev.repository.service;

import java.util.Map;

public interface IEmailService {
    String enviarEmailTexto(String destinatario, String titulo, String mensagem);

    void enviarEmailTemplate(String destinatario, String titulo, Map<String, Object> propriedades);
}
