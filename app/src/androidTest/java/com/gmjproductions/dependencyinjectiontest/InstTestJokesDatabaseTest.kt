package com.gmjproductions.dependencyinjectiontest

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by garyjacobs on 2/26/18.
 */
@RunWith(AndroidJUnit4::class)
class InstTestJokesDatabaseTest {
    lateinit var jokeTypeKK: JokeType
    lateinit var jokeTypeYM: JokeType
    lateinit var jokeKK: Joke
    lateinit var jokeYourMother: Joke
    lateinit var jokeYourMother2: Joke
    lateinit var jokeCopy: Joke
    lateinit var db: JokesDatabase
    @Before
    fun setUpJokeData() {
        jokeTypeKK = JokeType("Knock-Knock")
        jokeTypeYM = JokeType("YourMother")
        jokeKK = Joke(10, jokeTypeKK.type, "Why did the chicken cross the road?", "To get to the other side.")
        jokeYourMother = jokeKK.copy(id = 5, type = jokeTypeYM.type)
        jokeYourMother2 = jokeYourMother.copy(id = 20, setup = "Your Mother's so fat...", punchline = "when sits around the room, she sits around the room!")
        jokeCopy = jokeKK
        db = JokesDatabase.getInstance(InstrumentationRegistry.getTargetContext())
    }

    @Test
    fun verifyJokeDataAnd() {
        assertNotNull(jokeTypeKK)
        assertNotNull(jokeTypeYM)
        assertNotNull(jokeKK)
        assertNotNull(jokeCopy)
        assertNotNull(jokeYourMother)
        assertEquals(jokeKK, jokeCopy)
        assertNotEquals(jokeKK, jokeYourMother)
        assertNotNull(db)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllJokesTest() {
        val list = db.jokesDao().getAllJokes()
        list?.let {
            db.jokesDao().deleteJokes(it)
        }
        assertEquals(db.jokesDao().getAllJokes().size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllJokeTypesTest() {
        val list = db.jokesDao().getAllJokeTypes()
        list?.let {
            db.jokesDao().deleteJokeTypes(it)
        }
        assertEquals(db.jokesDao().getAllJokeTypes().size, 0)
    }

    @Test
    @Throws(Exception::class)
    fun addJokeToDBTest() {
        db.jokesDao().insertJoke(jokeKK)
        db.jokesDao().insertJoke(jokeYourMother)
        val list = db.jokesDao().getAllJokes()
        assertNotNull(list)
        val foundJoke = list.find { it == jokeKK }
        assertNotNull(foundJoke)
        assertEquals(foundJoke, jokeKK)
        assertEquals(list.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun addJokeTypeToDBTest() {
        db.jokesDao().insertJokeType(jokeTypeYM)
        db.jokesDao().insertJokeType(jokeTypeKK)
        val list = db.jokesDao().getAllJokeTypes()
        val foundJokeType = list.find { it == jokeTypeYM }
        assertNotNull(foundJokeType)
        assertEquals(foundJokeType, jokeTypeYM)
        assertEquals(list.size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun findJokesOfTypeTest() {
        db.jokesDao().insertJoke(jokeYourMother2)
        val list = db.jokesDao().getJokesOfType(jokeTypeYM)
        assertNotNull(list)
        assertEquals(list.size, 2)
    }

}