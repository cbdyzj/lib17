<template>
  <div class="search-form">
    <AForm layout="inline" :form="form" @submit="handleSubmit">
      <AFormItem label="Name">
        <AInput type="text"
            style="width: 240px" v-decorator="['name']" placeholder="Please input sample name"/>
      </AFormItem>
      <AFormItem label="Type">
        <ASelect
            style="width: 240px"
            v-decorator="['type',{ rules: [{ required: true, message: 'Please select sample type!' }] }]"
            placeholder="Please select sample type"
        >
          <ASelectOption value="all">All</ASelectOption>
          <ASelectOption value="qualified">Qualified</ASelectOption>
          <ASelectOption value="unqualified">Unqualified</ASelectOption>
        </ASelect>
      </AFormItem>
      <AFormItem>
        <AButton :loading="searchLoading" style="height: 40px" icon="search" type="primary" html-type="submit">
          Search
        </AButton>
      </AFormItem>
    </AForm>
  </div>
</template>

<script>
export default {
  props: {
    searchLoading: {
      type: Boolean,
      default() {
        return false
      }
    }
  },
  data() {
    return {
      form: this.$form.createForm(this, { name: 'coordinated' }),
    }
  },
  methods: {
    handleSubmit(ev) {
      ev.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          this.$emit('search', {
            ...values,
            type: values.type === 'all' ? '' : values.type,
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.search-form {
  height: 120px;
  padding: 40px;
  background-color: #fff;
  border-radius: 10px;
}
</style>