//var val = 25;
// let sum = 0;

// while (val) {
//     sum += (val % 10);
//     console.log(sum);
// }  

var value = 2568,
    sum = 0;

while (value) {
    sum += value % 10;
    console.log(sum);
    console.log(value)
    value = Math.floor(value / 10);
    console.log(value);
}

console.log(sum);
