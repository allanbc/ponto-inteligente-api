package com.allanbc.pontointeligente.api.repositories;

import com.allanbc.pontointeligente.api.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

        @Transactional(readOnly = true)
        Empresa findByCnpj(String cnpj);
}
