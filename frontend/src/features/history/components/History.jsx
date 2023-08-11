import Parchment from "@component/Parchment";
import React from "react";
import HistoryHeader from "./HistoryHeader";
import HistoryRounds from "./HistoryRounds";
import { testData } from "./test";
import HistoryResult from "./HistoryResult";

const History = () => {
  const data = testData;
  return (
    <Parchment style={{ width: "912px", height: "973px", overflow: "auto" }}>
      <HistoryHeader
        myName={data.hostNickName}
        opponentName={data.guestNickName}
      />
      <HistoryRounds
        roundLogResponses={data.gameLogResponse.roundLogResponses}
      />
    </Parchment>
  );
};

export default History;
