<template>
  <div>
    <form>
      <input v-model="student.email" type="email" placeholder="Email" /><br />
      <input v-model="student.fullname" type="text" placeholder="Fullname" /><br />
      <input v-model="student.marks" type="text" placeholder="Average marks" /><br />
      <input v-model="student.gender" type="radio" value="true" name="gender" />Male
      <input v-model="student.gender" type="radio" value="false" name="gender" />Female <br />
      <input type="hidden" v-model="keystudent" />
      <select v-model="student.country" name="country">
        <option value="VN">VN</option>
        <option value="US">US</option>
      </select>
      <br /><br />
      <button type="button" @click="axiosCreateStudent()">Create</button>
      <button type="button" @click="axiosUpdateStudent(keystudent)">Update</button>
      <button type="button" @click="axiosDeleteStudent(keystudent)">Delete</button>
      <button type="reset">Reset</button>
      <hr />
    </form>
    <table border="1">
      <thead>
        <tr>
          <th>Email</th>
          <th>Fullname</th>
          <th>Average marks</th>
          <th>Gender</th>
          <th>Country</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="[key, student] in Object.entries(students)" :key="key">
          <td>{{ student.email }}</td>
          <td>{{ student.fullname }}</td>
          <td>{{ student.marks }}</td>
          <td>{{ student.gender === 'true' ? 'Male' : 'Female' }}</td>
          <td>{{ student.country }}</td>
          <td>
            <button @click="axiosEditStudent(key)">Edit</button>
            <button @click="axiosDeleteStudent(key)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const hostFireBase = import.meta.env.VITE_FIREBASE_HOST
const keystudent = ref('')
const students = ref([])
const student = ref({
  email: '',
  fullname: '',
  marks: '',
  gender: '',
  country: '',
})

const axiosDataStudent = async () => {
  try {
    const response = await axios.get(`${hostFireBase}/students.json`)
    students.value = response.data
  } catch (error) {
    console.error(error)
  }
}

const axiosEditStudent = async (key) => {
  try {
    const response = await axios.get(`${hostFireBase}/students/${key}.json`)
    student.value = response.data
    keystudent.value = key
  } catch (error) {
    console.error(error)
  }
}

const axiosCreateStudent = async () => {
  try {
    await axios.post(`${hostFireBase}/students.json`, student.value)
    alert('Create success')
    await axiosDataStudent()
  } catch (error) {
    console.error(error)
  }
}

const axiosUpdateStudent = async (key) => {
  try {
    await axios.put(`${hostFireBase}/students/${key}.json`, student.value)
    alert('Update success')
    await axiosDataStudent()
  } catch (error) {
    console.error(error)
  }
}

const axiosDeleteStudent = async (key) => {
  try {
    await axios.delete(`${hostFireBase}/students/${key}.json`)
    alert('Delete success')
    await axiosDataStudent()
  } catch (error) {
    console.error(error)
  }
}

onMounted(async () => {
  await axiosDataStudent()
  console.log(students.value)
})
</script>
