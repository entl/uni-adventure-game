package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;
import com.university.utils.UI.UIManager;

public class SausageStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* The CutieCat is full and happy! She lets you pass.");
        UIManager.getInstance().displayMessage("""
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
