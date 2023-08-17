import { ResponsiveLine } from "@nivo/line";
import { useEffect, useState } from "react";
import { getHistoryTableRow } from "../api/getTableRow";
import { useSelector } from "react-redux";

const RatingGraph = () => {
  const [data, setData] = useState([]);

  const { email } = useSelector((state) => state.user);

  const fetchLast10Rating = async () => {
    try {
      let newData = await getHistoryTableRow(email, 0);
      newData.reverse();
      if (!newData) {
        return;
      }

      if (newData.length < 10) {
        newData = [
          ...Array.from({ length: 10 - newData.length }, (v, i) => ({
            postRating: 100000000,
          })),
          ...newData,
        ];
      }

      const ratings = newData.reduce((acc, rating, index) => {
        return [...acc, { x: newData.length - index, y: rating.postRating }];
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
    <div style={{ width: "800px", height: "480px" }}>
      <ResponsiveLine
        fontFamily={"NexonGothic"}
        data={data}
        margin={{ top: 50, right: 10, bottom: 50, left: 60 }}
        xScale={{ type: "linear", reverse: true }}
        yScale={{ type: "linear", stacked: true, min: "auto", max: "auto" }}
        axisTop={null}
        axisBottom={{
          tickValues: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1],
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
          legend: "현상금($)",
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
        gridXValues={[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]}
        gridYValues={[0, 40000000, 250000000, 1000000000, 4000000000]}
        theme={theme}
        tooltip={({ point }) => (
          <div style={{ fontFamily: "NexonGothic" }}>
            {`${point.data.x}게임 전, ${point.data.yFormatted}$`}
          </div>
        )}
      />
    </div>
  );
};

export default RatingGraph;
