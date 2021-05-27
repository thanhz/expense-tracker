import React, { useState, useEffect } from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Title from './Title';

function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles({
  depositContext: {
    flex: 1,
  },
});

export default function TotalExpense() {
  const [count, setCount] = useState(0);

  useEffect(() => {    
    fetch(`http://localhost:8080/expense/total`)
    .then(response => response.json()
    .then(data => setCount(data)))
    .catch(e => console.log(e))
    });

  const classes = useStyles();
  return (
    <React.Fragment>
      <Title>Total Expense</Title>
      <Typography component="p" variant="h4">
        £{count}
      </Typography>
      <Typography color="textSecondary" className={classes.depositContext}>
        on 15 March, 2019
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          View Expense
        </Link>
      </div>
    </React.Fragment>
  );
}