import Parchment from "@component/Parchment";
import React from "react";
import SummaryCard from "./SummaryCard";

const HistorySummary = () => {
  return (
    <Parchment
      style={{
        width: "100%",
        height: "100%",
        display: "flex",
        justifyContent: "space-between",
      }}>
      <SummaryCard title="win" value="20" />
      <SummaryCard title="lose" value="20" />
      <SummaryCard title="winrate" value="50%" />
    </Parchment>
  );
};

export default HistorySummary;
