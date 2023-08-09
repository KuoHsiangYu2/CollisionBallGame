# CollisionBallGame

<p>
    參考網路資料寫了簡單小遊戲。<br />
    Ball類別 與 Racquet類別 繼承 JPanel類別。<br />
    接著覆寫paint方法畫出 Ball（球） 跟 Racquet（球拍）。<br />
    CollisionBallGame類別 繼承 JFrame類別 繪製GUI主視窗出來。<br />
    再把 Ball 跟 Racquet 加入 JFrame裡。<br />
    為了讓左右方向鍵按下去能夠移動 球拍 ，<br />
    CollisionBallGame類別 有 實作 KeyListener介面，<br />
    覆寫 keyPressed（鍵盤按下去的事件）方法、keyReleased（放開鍵盤事件）方法以及在JFrame註冊鍵盤監聽器，去監聽鍵盤動作的相關event。<br />
</p>
<br />

<p>
    參考資料：<br />
    <a href="https://hackmd.io/@tiffany0226/java_game" target="_blank">
        三個Java小程式做出簡易小遊戲
    </a>
</p>