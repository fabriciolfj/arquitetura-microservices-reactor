package com.github.fabriciolfj.inventory.adapters.repository;

import com.github.fabriciolfj.inventory.core.documents.Extrato;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ExtratoRepository extends ReactiveMongoRepository<Extrato, String> {

    Mono<Extrato> findFirstByCodeOrderByMovDesc(final String code);
}
