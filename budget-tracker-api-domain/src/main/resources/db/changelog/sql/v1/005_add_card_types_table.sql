create table card_types (
    id   bigserial   not null,
    name varchar(32) not null,
    icon bytea       not null,

    constraint card_types_id_pkey primary key (id),
    constraint card_types_name_unique unique (name)
);

insert into card_types (name, icon)
values ('Visa', decode('iVBORw0KGgoAAAANSUhEUgAAADAAAAARCAYAAACb8i8UAAAAIGNIUk0AAHomAACAhAAA+gAAAIDoAAB1MAAA6mAAADqYAAAXcJy6UTwAAAAGYktHRAD/AP8A/6C9p5MAAAAJcEhZcwAACxIAAAsSAdLdfvwAAAAHdElNRQfnBhkWBirwjt29AAAAAW9yTlQBz6J3mgAAB+tJREFUSMeFlmvMHVUVhp93z5zzXdqvXIoUhBbCZQahEopoz3CpFeEHknBR+UEkagSimMB8aMQQNcRLovwgPcdgwFowgSASE5EYCAEiV51jFFBJgDnIXQsWSul3PefM7L38cU6LDSTOr509e6+13r3e9a4F/+dbvOVwASxsWwvAcdkNJK3O+84lrfYH3t+zn2Qd0lY7SlsdfdC5NBvtp1mHNOvs2XNJtq+vZONPSbP3fMU7bjp6cqpRHSls6M0t1sF5J3OCaFd/aueKS58bLt6yVisufd0AgsX0ujlp1m6a4RAmw5m0nLbaDmggMAOg6nVnQ5J11CtyA/wIVGe1xAzgMFsy6T9lkVuadSiLfC+gssjDaN2eMiNIAH5QFleTZlvAHHFtrhlMm2NnFzSdP7Xh/Epgl2SNNSvqF97desT5Ky599V+7th6pjbderV43D2nWPhPj54g5GQeY+F2vyL+Rtto3mDhPxoJEZXAesL1X5Ja02gguM3ExxvFmtlqSQ5oDXk9b7WvLIr8vGT0CZZGHJGufJONGjP0lKjOmQVcCD5jJ9bp5iD/89RfmgJuBm3f/4ogzZpr9qxS7z5uP0LROtt3hImDLZFxFkgUAjK9FzVXH+GoB15jC10uPAyCdHcXTR1nwWBjsELZ7TJ8DMe52jelNFmrAkCLAsFAfEMUrDqiHc6vHrJAkP17eFDVnWr5eAoxGY4a62n0W8ADg0qwT3Fs3H8X8tnVu3Sk/Y7/LX33cXXZv/sQrR14h1+8ROYR9BOCe8qOUxWxIW511oHPqai5IDl8tPdUr8ruTrH2IYQeGeglGOJ8qi9nFMeBtcXPlplAv9s1qgq92eL/8qPf9xy3UL/lqAeCZcdCuLHKSrL1Zilp+OFeZ1d7MD71fBuxEACFvGC6YWLnxtXDIynkHsCF7+JjP/uYrX7z2ofNzFhef86azAb7zyGdGrBYXuXhqJWgo1wC4dex4nYgOxaxS1MDMXgJIW+0TJS6sq4VarjlJCH8B0l4xu7lXzG4CNmDhDMHLI/PjLKMrnGuM8oEiUGxWY3BSknVWld3cQHJrrngRXo5ZrhsB4OnHvvXYwSvm1972zCm3X/fwud9dOdHfXW5Zf+yLf77K/+qacxxmX7BQITQZ/PKbwF3j1B/tXAODgAWEynEgh0sxMgwzDKYQ0R4VKbuzc2U3f6Ls5rvTrOPK7qxPs85xgguC72MgM+4c3Q8It0bGcQAy5ADSH9/AM098kzRrRwCVj+780Kqpg2568vTvP/xyenuy+q0E4Pri02fi4g3B6qGLJjG4o9fN3x4ZsxOQkBSFMMRkzwKYeDZYPUBqWKiHzjXWY5RJq31NkrVXjbLUicdyO5JYs8tdNNlEDsxeRPwEMQ/goiaGfXzETHMOoDfi3F5tleyR/mCeA6fr9Vc/eMFFby7MvAIwqONL3Ig2UaiXatke+oCh48fOnVkYgJ4f235F8G0XTSIXNy0MB5JWR/H09ULPJq32hWU3r4Wissh90mqvNrgk+D7OTQDc1yvyfxhsR9GI/dLJY7fB7QnAITCNVUaFhfAGNmRgqzZ+8rYrT9989g+mgumC4PtEbiIy9PuyO/tsmrWVtDqRxJEWanCRE/aqsLfHjUxlkXdCvXSxhfo1F09PAAS/NJTiw1w08duk1f5c2c33KM8lUTx1MFgd6mUDbh/XxpNSTAgVZpyStDr0urPvAQiAgaVZ25XdfJdkf5JrgtVIfGn7/H6XRvHkfmZUZh5g60ivZ01wMMYRZjWjOtA/y2K2n7baDsklWTsqu7O/Bk7w9fKsWdiJoqZZNRgHfd24wUWCy0OokBqxiQL0QprdKDP9XRIEb4ijEetGDz/+ekWORiDGPNT9kiP4KoCtl+y64Pu4qNEwq57sdfP701Znz/3DcNGBGBU4MHrjAg29Ive9YtYnWTtGttDr5h3EaZgfgCYs1CDWJFlnUti5iiZOMPO1WQ2QIv5q5p8Hrh4VtQWnxgrBBoD4f+eMspuTZp0wlstHQ90fSJoAZkAzZqFyihoB/WLEexrAwLDjnSJMNePslGMJPRWUIu4ui/zdvTWGDjVwMoKcc1jYURZ5P2l1rnTjV0TCueZqya0ebRjBDwCCokZk1XADcM8+ANJWBwxLsjZlkb+QZJ2/ORdvNF9VECQXNXy9/AbSXeNiD+OAjpccARnmETw3SqI+1Wjs96N6uPuHSavzCrKdggPAWkINZJWLp1w9nN+SZJ1Ucmf5uu8lF4PtDGGwXUZswjAMWIc0baHGpE/sQ6G9aiKQjXRaxkMumgDRQIqjaBqhO3pF/m6StSNs1PJNdgJyCNe0UC+DvTw2d7Jh4KLDomjitCiaPM9Fk2eguCE3gYumGr6a29rr5rfK7HtRNIWkSC4G+HKvyE9EbBTW6nXz9cAv43hFhAVkfCxpdWb2AVB2c3pFjgkb6+z9dTW/y+AtjJ11Pf+GwbYxEazs5iHNOk2h/X21tAhhEfSUSTvGJh/01e6nMf9O8IO+rwfD4AfLZn5H8P17fb18flnMfjXJ2mtNbPLVwjtgC8EP/2hw70gk8nlM82Ml+oOvFgbB6h0mAzj2fbN5knXoFTlpq0PZzUmy9oRMDhlmqnvdvBrN446yuGrPHD9jhhCSUZXdfHEfambtgzAdajAhsWjw716Rz43+dcCsaTCJ8AJnRr/Xna3SrK1R/0aSDEOGTSCQScDwv1DZIyzP+WFSAAAAJXRFWHRkYXRlOmNyZWF0ZQAyMDIzLTA2LTI1VDIyOjA2OjI2KzAwOjAwq4S+lgAAACV0RVh0ZGF0ZTptb2RpZnkAMjAyMy0wNi0yNVQyMjowNjoyNiswMDowMNrZBioAAAAodEVYdGRhdGU6dGltZXN0YW1wADIwMjMtMDYtMjVUMjI6MDY6NDIrMDA6MDC/7AphAAAAAElFTkSuQmCC', 'base64')),
       ('Mastercard', decode('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', 'base64')),
       ('Uzcard', decode('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', 'base64'));
