package samples.linhtruong.com.playground.java.google_arch.test.room_test;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * CLASS DESCRIPTION
 *
 * @author linhtruong
 * @date 8/22/18 - 14:41.
 * @organization VED
 */

@Dao
public interface RepoDAO {
   @Query("SELECT * FROM repo")
   List<Repo> getAllRepos();

   @Insert
   void insert(Repo... repos);

   @Update
   void update(Repo... repos);

   @Delete
   void delete(Repo... repos);
}
