import { ResponsiveLine } from "@nivo/line";
import { useEffect, useState } from "react";
import { getHistoryTableRow } from "../api/getTableRow";
import { useSelector } from "react-redux";

const RatingGraph = () => {
  const [data, setData] = useState(null);

  const { email } = useSelector((state) => state.user);

  const fetchLast10Rating = async () => {
    try {
      // 임시데이터
      const newData = [
        { postRating: 40000000 },
        { postRating: 250000000 },
        { postRating: 1000000000 },
        { postRating: 4000000000 },
        { postRating: 4000000000 },
        { postRating: 4000000000 },
        { postRating: 4000000000 },
        { postRating: 4000000000 },
        { postRating: 1000000000 },
        { postRating: 1000000000 },
        { postRating: 1000000000 },
      ];

      // const newData = await getHistoryTableRow(email, 1);
      const ratings = newData.reduce((acc, rating, index) => {
        return [...acc, { x: index, y: rating.postRating }];
      }, []);

      setData([{ id: "rating", color: "black", data: ratings }]);
    } catch (error) {
      console.log("error:", error);
    }
  };

  useEffect(() => {
    fetchLast10Rating();
  }, []);

  const theme = {
    axis: {
      ticks: {
        text: {
          fontSize: 18,
          fill: "black",
          outlineWidth: 0,
          outlineColor: "transparent",
        },
      },
      legend: {
        text: {
          fontSize: 20,
        },
      },
    },
    grid: {
      stroke: "black",
      strokeWidth: 10,
    },
  };

  return (
    <ResponsiveLine
      data={data}
      margin={{ top: 50, right: 160, bottom: 50, left: 60 }}
      xScale={{ type: "linear" }}
      yScale={{ type: "linear", stacked: true, min: "auto", max: "auto" }}
      axisTop={null}
      axisBottom={{
        tickValues: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        legend: "게임",
        legendOffset: 36,
        legendPosition: "middle",
      }}
      axisLeft={{
        tickValues: [0, 40000000, 250000000, 1000000000, 4000000000],
        tickSize: 5,
        tickPadding: 5,
        tickRotation: 0,
        format: ".2s",
        legend: "현상금",
        legendOffset: -40,
        legendPosition: "middle",
      }}
      enableGridX={true}
      colors={"black"}
      lineWidth={5}
      pointSize={10}
      pointColor={"black"}
      pointBorderWidth={1}
      pointBorderColor={{ from: "serieColor" }}
      pointLabelYOffset={-12}
      useMesh={true}
      gridXValues={[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}
      gridYValues={[0, 40000000, 250000000, 1000000000, 4000000000]}
      theme={theme}
    />
  );
};

export default RatingGraph;
