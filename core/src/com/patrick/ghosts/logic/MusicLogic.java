package com.patrick.ghosts.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.patrick.ghosts.util.Settings;

public class MusicLogic {
    /**
     * SPOOKY:
     *
     * https://www.beepbox.co/#8n31sbk1l00e0dt2mm0a7g0fj07i0r1o2210T6v1L4u74q1d1f8y2z1C1c0W42T1v1L4u72q1d1f9y0z8C1c0A1FhB8V8Q4154Pd567E0111T6v1L3u77q1d1f7y4z1C1c2W79T2v1L4u15q0d1f8y0z1C2w0b4zhlllpllg0id3cPd3cP00018PcRcPc0018Qh4h4h00p246FHYEbVgLJbV8LM1vaBZxv95-hbQQv51vAkkkkReEb0BcwbgbisjN0LGbWO-ILONvEJ1vypvyd1ukQuMQkkkkkkkkkkkkkko2yyyyyyyyyyyyyyzhhhhhhhgCnyyyyyyyyyyyyyyyzw5555d555d5541hhg4XZdN-gjLVaCOZiJd7GgC8oxye8Y2EN62N34c_40FEPzCW3zWd170k4t97jghQAtd17jEFFFF8Wq5cLL8An8JFFFQ4th7ihQAt97ihQAt960
     *
     *
     * SAD:
     *
     * https://www.beepbox.co/#8n50sbk6l00e0Et1Dm3a7g0Gj09i0r1o32314T5v0L4u32q1d5f8y1z7C1c0h0HU7000U0006000ET1v1L4u91q1d4f6y2z1C0c2AbF6B8V9Q28c0Pb745E0001T5v1L4u20q1d5f6y3z8C0c0h1H_SRJ4AAAwAAAAbT6v0L4u77q1d1f7y4z1C1c2W79T5v0L4u21q1d5f6y2z8C0c0h1H_SJ5JJEAAA3rrrb4zhmu18QlDwid5pU4zhmu04zhmu00000000000018Q4Boid19ms00000018j4xgi4N8k4xci518j4xhh8j4Cs0000g018j4x8i4N8i4xci4A00000000000000000000000id5pU0id90000p24lFH-G92hjsvEV6jwqHWsHAkkR9JvPews98idupy4JGEGLNb8IMdk1prtv0UagGHGGGIFLh-yCLUEEEjq_43wo6ODAQgszBlYSSGL8HRRnMRdWfFkTEV0FH_HGGVCxoe5P72gk9jln9Ve5wUm5FwUfcQb1RFKDNarQvyVKDSMke153KrQv1425CWvyE4xITE-2gsm-18bdWerE5dvRh8iarzZ78Os3lvjBsyyCFdH-pQ3x92hHPcgBJl5l-9p5C1GwbbrHU71i5ltlllBdWfRBw
     *
     *
     */

    Music music;

    public MusicLogic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("songs/sad.wav"));
        music.setLooping(true);
        music.setVolume(.5f);
    }

    public void play() {
        if(Settings.PLAY_MUSIC)
        music.play();
    }

    public void stop() {
        music.stop();
    }

    public void pause() {
        music.pause();
    }

    public void dispose() {
        music.dispose();
    }

    public void switchMusic(String song) {
        if(Settings.PLAY_MUSIC) {
            String file = song + ".wav";
            Music newMusic = Gdx.audio.newMusic(Gdx.files.internal(file));
            music = newMusic;
            music.setLooping(true);
            music.setVolume(.5f);
            music.play();
        }
    }
}
