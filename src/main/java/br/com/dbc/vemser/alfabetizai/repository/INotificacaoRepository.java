package br.com.dbc.vemser.alfabetizai.repository;


import br.com.dbc.vemser.alfabetizai.enums.TipoStatus;
import br.com.dbc.vemser.alfabetizai.models.Log;
import br.com.dbc.vemser.alfabetizai.models.Notificacao;
import br.com.dbc.vemser.alfabetizai.models.NotificacaoContador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificacaoRepository extends MongoRepository<Notificacao, String> {
  List<Notificacao> findAllByTipoStatus(TipoStatus tipoStatus);

  Page<Notificacao> findAll(Pageable pageable);

  @Aggregation(pipeline = {
      "{$unwind: '$tipoStatus'}",
      "{$group: { _id: '$tipoStatus', quantidade: { $sum: 1 } }}"
  })
  List<NotificacaoContador> groupByTipoStatusAndCount();

  List<Notificacao> findAllByDataContains(String data);

  @Aggregation(pipeline = {
      "{$match: { data: { $regex: ?0 } }}",
      "{$group: { _id: '$tipoStatus', quantidade: { $sum: 1 } }}"
  })
  List<NotificacaoContador> findAllByDateAndCountTipoStatus(String date);

  Integer countAllByDataContains(String data);

  Integer countByTipoStatus(TipoStatus tipoStatus);

  @Query("{'data': { $gte: ?0 } }")
  List<Log> findAllAfterDate(String date);
}
