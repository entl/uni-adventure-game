package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;

public class SausageStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        System.out.println("* The CutieCat is full and happy! She lets you pass.");
        System.out.println("""
       /\\     /\\
      {  `---'  }
      {  O   O  }
      ~~>  V  <~~
       \\ \\|/ /
        `-----'____
        /     \\    \\_
       {       }\\  )_\\_   _
       |  \\_/  |/ /  \\_\\_/ )
        \\__/  /(_/     \\__/
          (__/
          \s""");
    }
}
