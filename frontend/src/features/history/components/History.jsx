import Parchment from "@component/Parchment";
import React, { useEffect, useState } from "react";
import HistoryHeader from "./HistoryHeader";
import HistoryRounds from "./HistoryRounds";
import HistoryResult from "./HistoryResult";
import { getHistoryDetails } from "../api/history";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import WoodBackground from "@component/WoodBackground";

const History = () => {
  const [history, setHistory] = useState(null);

  const email = useSelector((state) => state.user.email);
  const { historyId } = useParams();

  useEffect(() => {
    const fetchHistoryDetails = async () => {
      try {
        console.log(email);
        console.log(historyId);
        const historyDetails = await getHistoryDetails(email, historyId);
        setHistory(historyDetails);
      } catch (error) {
        console.error("Error fetching history details:", error);
      }
    };

    fetchHistoryDetails();
  }, [email, historyId]);

  return (
    <WoodBackground>
      <Parchment style={{ width: "912px", height: "973px", overflow: "auto" }}>
        {history !== null && (
          <>
            <HistoryHeader
              myName={history.myNickName}
              opponentName={history.opponentNickName}
            />
            <HistoryRounds
              roundLogResponses={history.gameLogResponse.roundLogResponses}
            />
            <HistoryResult result={history.result} />
          </>
        )}
      </Parchment>
    </WoodBackground>
  );
};

export default History;
