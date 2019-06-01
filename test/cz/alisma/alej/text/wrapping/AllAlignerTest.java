/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2019 Vojtech Travnicek, Michaela Arnostova
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cz.alisma.alej.text.wrapping;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AllAlignerTest {

    @Test
    public void leftAlignerTest() {
        Aligner aligner = new LeftAligner();
        List<String> words = new ArrayList<String>();
        words.add( "hello" );
        words.add( "world" );
        String result = aligner.format( words , 16 );
        assertEquals("Whoa", "hello world", result);
    }
    
    @Test
    public void rightAlignerTest() {
        Aligner aligner = new RightAligner();
        List<String> words = new ArrayList<String>();
        words.add( "hello" );
        words.add( "world" );
        String result = aligner.format( words , 16 );
        assertEquals("Whoa", "     hello world", result);
    }
    
    @Test
    public void centerAlignerTest() {
        Aligner aligner = new CenterAligner();
        List<String> words = new ArrayList<String>();
        words.add( "hello" );
        words.add( "world" );
        String result = aligner.format( words , 17 );
        assertEquals("Whoa", "   hello world", result);
    }
    
    @Test
    public void justifyAlignerTest() {
        Aligner aligner = new JustifyAligner();
        List<String> words = new ArrayList<String>();
        words.add( "hello" );
        words.add( "world" );
        words.add(":)");
        String result = aligner.format( words , 16 );
        assertEquals("Whoa", "hello  world  :)", result);
    }
    
    @Test
    public void justifyAlignerTest2() {
        Aligner aligner = new JustifyAligner();
        List<String> words = new ArrayList<String>();
        words.add( "hello" );
        words.add( "world" );
        words.add(":)");
        String result = aligner.format( words , 17 );
        assertEquals("Whoa", "hello   world  :)", result);
    }
    
    @Test
    public void justifyAlignerTest3() {
        Aligner aligner = new JustifyAligner();
        List<String> words = new ArrayList<String>();
        words.add( "Tohle" );
        words.add( "je" );
        words.add("rozvrstvene");
        words.add("dobre.");
        String result = aligner.format( words , 30 );
        assertEquals("Whoa", "Tohle  je  rozvrstvene  dobre.", result);
    }
}