package com.example.td6;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface RepoDao {
    @Query("SELECT * FROM Repo")
    Observable<List<Repo>> getRepos();

    @Query("SELECT * FROM Repo WHERE id = :id")
    Observable<List<Repo>> getReposByID(int id);

    @Delete
    Completable deleteRepo(Repo repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Observable<Long> insertOrUpdateRepo(Repo repo);
}