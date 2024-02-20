package br.com.dbc.vemser.alfabetizai.repository;


import br.com.dbc.vemser.alfabetizai.enums.TipoLog;
import br.com.dbc.vemser.alfabetizai.models.Log;
import br.com.dbc.vemser.alfabetizai.models.LogContador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILogRepository extends MongoRepository<Log, String> {
  List<Log> findAllByTipoLog(TipoLog tipoLog);

  Page<Log> findAll(Pageable pageable);

  @Aggregation(pipeline = {
      "{$unwind: '$tipoLog'}",
      "{$group: { _id: '$tipoLog', quantidade: { $sum: 1 } }}"
  })
  List<LogContador> groupByTipoLogAndCount();

  List<Log> findAllByDataContains(String data);

  @Aggregation(pipeline = {
      "{$match: { data: { $regex: ?0 } }}",
      "{$group: { _id: '$tipoLog', quantidade: { $sum: 1 } }}"
  })
  List<LogContador> findAllByDateAndCountTipoLog(String date);

  Integer countAllByDataContains(String data);

  Integer countByTipoLog(TipoLog tipoLog);

  @Query("{'data': { $gte: ?0 } }")
  List<Log> findAllAfterDate(String date);
}
