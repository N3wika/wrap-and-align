/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2017 Vojtech Horky
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

import java.util.Scanner;

public class WrapAndAlign {

    private static final int MAX_WIDTH = 50;

    enum Alignment {
        Left, Right, Justify, Center
    };


    public static void main( String[] args ) {
        Scanner input = new Scanner( System.in );
        ParagraphDetector pd = new ParagraphDetector( input );

        //LeftAligner je defaultni zarovnani, MAX_WIDTH je defaultni delka
        Aligner aligner = new LeftAligner();
        int delkaRadek = MAX_WIDTH;

        Alignment alignment = Alignment.Left;

        for ( String prepinacZarovnani : args ) {
            switch ( prepinacZarovnani ) {
                case "--left":
                    alignment = Alignment.Left;
                    break;
                case "--right":
                    alignment = Alignment.Right;
                    break;
                case "--justify":
                    alignment = Alignment.Justify;
                    break;
                case "--center":
                case "--centre":
                    alignment = Alignment.Center;
                    break;
            }
        }

        boolean dalsiJeDelka = false;
        for ( String prepinacDelka : args ) {
            if ( dalsiJeDelka == true ) {
                delkaRadek = Integer.parseInt( prepinacDelka );
            }
            if ( prepinacDelka.startsWith( "--width" ) ) {
                String[] oddeleniDelky = prepinacDelka.split( "=" );
                delkaRadek = Integer.parseInt( oddeleniDelky[1] );
            }
            switch ( prepinacDelka ) {
                //pripraveno pro moznost pridani dalsiho prepinace 
                case "-w":
                    dalsiJeDelka = true;
                    break;
            }
        }

        switch ( alignment ) {
            case Center:
                aligner = new CenterAligner();
                break;
            case Justify:
                aligner = new JustifyAligner();
                break;
            case Left:
                aligner = new LeftAligner();
                break;
            case Right:
                aligner = new RightAligner();
                break;
        }

        while ( pd.hasNextParagraph() ) {
            Paragraph para = pd.nextParagraph();
            LinePrinter line = new LinePrinter( System.out, delkaRadek, aligner );
            while ( para.hasNextWord() ) {
                String word = para.nextWord();
                line.addWord( word );
            }
            line.flush();
            System.out.println();
        }
    }
}
