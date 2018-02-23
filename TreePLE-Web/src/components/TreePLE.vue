<template>
  <div id="treePLE">
    <h1>Welcome to TreePLE! </h1>
    <table>
      <tr v-for="tree in trees" >
        <td>
          ID:{{tree.id}}
          Species:{{tree.species}}
          Height:{{tree.height}}
          Diameter:{{tree.diameter}}
          Age:{{tree.age}}
          DatePlanted:{{tree.date}}
        </td>
      </tr>
      <tr>
          <td>
              <button @click="listAllTrees()">List All Trees</button>
          </td>
      </tr>
    </table>
    <p>
      <span style="color:red">Error: Message text comes here</span>
    </p>
  </div>
</template>
<script src="./treePLEsystem.js">
</script>

<style>
  #treePLE {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    background: #f2ece8;
  }
</style>

