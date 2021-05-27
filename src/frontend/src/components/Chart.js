import React,{useState, useEffect} from 'react';
import { PieChart, Pie, Cell, ResponsiveContainer } from 'recharts';
import Title from './Title';

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

export default function Chart() {
  const [costs, setList] = useState([]);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    fetch(`http://localhost:8080/expense/total/type`)
      .then((response) => response.json()
      .then((data) => setList(data)))
      .catch((e) => console.log(e));

      fetch(`http://localhost:8080/expense/total`)
      .then(response => response.json()
      .then(data => setTotal(data)))
      .catch(e => console.log(e))
  }, []);


  return (
    <React.Fragment>
    <Title>Overview</Title>
    <ResponsiveContainer width="100%" height="100%">
      <PieChart>
        <Pie
          data={costs}
          cx="50%"
          cy="50%"
          labelLine={true}
          label={(entry) => `${entry.type} ${entry.value/total * 100}%`}
          outerRadius={80}
          fill="#8884d8"
          dataKey="cost"
        >
          {costs.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
          ))}
        </Pie>
      </PieChart>
    </ResponsiveContainer>
    </React.Fragment>
  );
}