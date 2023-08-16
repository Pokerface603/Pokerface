import Parchment from "@component/Parchment";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import WoodBackground from "@component/WoodBackground";
import GameResultHeader from "./GameResultHeader";
import GameResultRounds from "./GameResultRounds";
import GameResultFooter from "./GameResultFooter";
import { getGameResultData } from "../api/result";

const GameResult = () => {
  const [history, setHistory] = useState(null);

  const email = useSelector((state) => state.user.email);

  useEffect(() => {
    const fetchHistoryDetails = async () => {
      try {
        console.log(email);
        const gameResultDetails = await getGameResultData(email);
        setHistory(gameResultDetails);
      } catch (error) {
        console.error("Error fetching history details:", error);
      }
    };

    fetchHistoryDetails();
  }, [email]);

  return (
    <WoodBackground>
      <Parchment style={{ width: "912px", height: "973px", overflow: "auto" }}>
        {history !== null && (
          <>
            <GameResultHeader
              myName={history.myNickName}
              opponentName={history.opponentNickName}
            />
            <GameResultRounds
              roundLogResponses={history.gameLogResponse.roundLogResponses}
            />
            <GameResultFooter result={history.result} />
          </>
        )}
      </Parchment>
    </WoodBackground>
  );
};

export default GameResult;
